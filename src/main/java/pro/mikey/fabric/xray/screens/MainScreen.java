package pro.mikey.fabric.xray.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import pro.mikey.fabric.xray.records.BlockEntry;
import pro.mikey.fabric.xray.records.BlockGroup;
import pro.mikey.fabric.xray.storage.Stores;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AbstractScreen {
  private final List<BlockGroup> blocks = new ArrayList<>();

  public MainScreen() {
    super(LiteralText.EMPTY);

    List<BlockGroup> read = Stores.BLOCKS.read();
    if (read != null) {
      this.blocks.addAll(read);
    }

    System.out.println("Caching block list");
  }

  @Override
  public void init(MinecraftClient client, int width, int height) {
    super.init(client, width, height);
  }

  @Override
  public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
    super.render(matrices, mouseX, mouseY, delta);

    int y = 50;
    for (BlockGroup group : this.blocks) {
      drawStringWithShadow(matrices, this.textRenderer, "Hello", this.width / 2 - 40, y, 0xFFFFFF);

      y += this.textRenderer.fontHeight + 10;
      for (BlockEntry entry : group.getEntries()) {
        drawStringWithShadow(
            matrices, this.textRenderer, entry.getName(), this.width / 2 - 40, y, 0xFFFFFF);

        matrices.push();
        matrices.translate(this.width / 2f - 60, y - 5, 0);
        matrices.scale(.8f, .8f, .8f);
        this.itemRenderer.renderInGui(new ItemStack(Items.GOLD_BLOCK), 0, 0);
        matrices.pop();

        y += this.textRenderer.fontHeight + 10;
      }

      y += 10;
    }
  }

  @Override
  public boolean isPauseScreen() {
    return false;
  }

  @Override
  public void onClose() {
    this.blocks.clear();
    super.onClose();
  }

  //  private static class ScrollingList extends EntryListWidget<ScrollingList.Entry> {
  //    public ScrollingList(
  //        MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
  //      super(client, width, height, top, bottom, itemHeight);
  //    }
  //
  //    public static class Entry extends EntryListWidget.Entry<ScrollingList.Entry> {
  //
  //      @Override
  //      public void render(
  //          MatrixStack matrices,
  //          int index,
  //          int y,
  //          int x,
  //          int entryWidth,
  //          int entryHeight,
  //          int mouseX,
  //          int mouseY,
  //          boolean hovered,
  //          float tickDelta) {}
  //    }
  //  }
}
