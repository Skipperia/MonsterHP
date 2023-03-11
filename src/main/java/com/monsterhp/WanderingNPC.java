package com.monsterhp;

import lombok.Getter;
import lombok.Setter;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.api.coords.WorldPoint;

public class WanderingNPC {
    @Getter
    private final int npcIndex;

    @Getter
    private final String npcName;

    @Getter
    @Setter
    private NPC npc;

    @Getter
    @Setter
    private WorldPoint currentLocation;

    @Getter
    @Setter
    private double currentHp;

    @Getter
    @Setter
    private boolean isDead;

    @Getter
    @Setter
    private int offset;

    final String ATTACK_FIELD = "Attack";


    WanderingNPC(NPC npc) {
        this.npc = npc;
        this.npcName = npc.getName();
        this.npcIndex = npc.getIndex();
        this.currentLocation = npc.getWorldLocation();
        this.currentHp = 100;
        this.isDead = false;
        this.offset = 0;
    }

    public boolean isItAttackable() {
        boolean isNPCAttackable = false;
        NPCComposition npcComposition = npc.getComposition();
        //For some reason the wandering npc list has some npcs with no nam, no composition and -1 combat level
        if (npcComposition != null) {
            String[] npcActions = npc.getComposition().getActions();
            for (String npcAction : npcActions) {
                if (ATTACK_FIELD.equals(npcAction)) {
                    isNPCAttackable = true;
                    break;
                }
            }
        }
        return isNPCAttackable;
    }

}