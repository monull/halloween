package io.github.monull.halloween.phase;

import io.github.monull.halloween.HalloweenMod;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class HalloweenPhase {
    private final ArrayList<Entity> remain = new ArrayList<>();
    private int maxValue = 0;
    private CommandBossBar bossBar;
    private BlockPos pos;
    private boolean wither = false;
    private WitherEntity witherEntity;
    private boolean title = true;

    int phase = 1;

    public void start(BlockPos pos, int zombie, int skeleton, boolean wither) {
        this.pos = pos;
        ServerWorld serverWorld = HalloweenMod.world;
        CommandBossBar bossBar;
        if (serverWorld.getServer().getBossBarManager().get(new Identifier("halloween", "bossbar")) != null) {
            bossBar = serverWorld.getServer().getBossBarManager().get(new Identifier("halloween", "bossbar"));
        } else {
            bossBar = serverWorld.getServer().getBossBarManager().add(new Identifier("halloween", "bossbar"), new LiteralText("remains : "));
        }
        serverWorld.getPlayers().forEach(player -> {
            assert bossBar != null;
            bossBar.addPlayers(serverWorld.getPlayers());
            bossBar.setVisible(true);
            bossBar.setColor(BossBar.Color.WHITE);
        });

        int dx;
        int dz;

        for (int x = 1; x <= skeleton; x++) {
            dx = new Random().nextInt(30) - 15;
            dz = new Random().nextInt(30) - 15;
            SkeletonEntity entity = new SkeletonEntity(EntityType.SKELETON, serverWorld);
            entity.setPos(pos.getX() + dx, pos.getY() + 2, pos.getZ() + dz);
            remain.add(entity);
            serverWorld.spawnEntity(entity);
        }


        for (int y = 1; y <= zombie; y++) {
            dx = new Random().nextInt(30) - 15;
            dz = new Random().nextInt(30) - 15;
            ZombieEntity entity1 = new ZombieEntity(EntityType.ZOMBIE, serverWorld);
            entity1.setPos(pos.getX() + dx, pos.getY() + 2, pos.getZ() + dz);
            remain.add(entity1);
            serverWorld.spawnEntity(entity1);
        }

        maxValue = remain.size();

        assert bossBar != null;
        bossBar.setName(new LiteralText("remains : " + String.format("%d", remain.size())));
        bossBar.setMaxValue(maxValue);
        bossBar.setValue(maxValue);
        this.bossBar = bossBar;
        if (wither) {
            this.wither = true;
            WitherEntity entity2 = new WitherEntity(EntityType.WITHER, serverWorld);
            entity2.setPos(pos.getX(), pos.getY() + 5, pos.getZ());
            serverWorld.spawnEntity(entity2);
            this.witherEntity = entity2;
        }
        update();
    }

    public void update() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            if (wither) {
                if (!witherEntity.isAlive()) {
                    ServerWorld serverWorld = HalloweenMod.world;
                    serverWorld.getPlayers().forEach(player -> {
                        if (title) {
                            player.networkHandler.sendPacket(new TitleS2CPacket(new LiteralText("§6 Happy Halloween!")));
                            title = false;
                        }
                    });
                }
            } else {
                for (int i = 0; i <= remain.size() - 1; i++) {
                    Entity entity = remain.get(i);
                    if (!entity.isAlive()) {
                        remain.remove(entity);
                    }
                }
                bossBar.setValue(remain.size());
                bossBar.setName(new LiteralText("remains : " + String.format("%d", remain.size())));
                if (remain.size() == 0) {
                    phase++;
                    if (phase == 2) {
                        start(pos, 5, 2, false);
                    } else if (phase == 3) {
                        start(pos, 5, 5, false);
                    } else if (phase == 4) {
                        start(pos, 10, 7, false);
                    } else if (phase == 5) {
                        start(pos, 10, 7, true);
                    }
                }
            }
        });
    }
}
