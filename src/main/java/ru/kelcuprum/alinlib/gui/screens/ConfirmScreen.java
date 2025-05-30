package ru.kelcuprum.alinlib.gui.screens;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
//#if MC >= 12106
import org.joml.Matrix3x2f;
import net.minecraft.client.renderer.RenderPipelines;
//#endif
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;

import java.util.List;


public class ConfirmScreen extends Screen {
    public final Screen parent;
    public final Component message;
    public final BooleanConsumer consumer;
    public final ResourceLocation icon;
    public String url;

    public ConfirmScreen(Screen parent, Component title, Component message, String url){
        this(parent, null, title, message, url);
    }
    public ConfirmScreen(Screen parent, ResourceLocation icon, Component title, Component message, String url){
        this(parent, icon, title, message, (bl) -> {
            if (bl) Util.getPlatform().openUri(url);
        });
        this.url = url;
    }
    public ConfirmScreen(Screen parent, Component title, Component message, BooleanConsumer consumer) {
        this(parent, null, title, message, consumer);
    }
    public ConfirmScreen(Screen parent, ResourceLocation icon, Component title, Component message, BooleanConsumer consumer) {
        super(title);
        this.consumer = consumer;
        this.message = message;
        this.parent = parent;
        this.icon = icon;
    }
    AbstractWidget yes;
    AbstractWidget no;
    AbstractWidget copy;


    @Override
    protected void init() {
        int x = (width / 2) - 104;
        int y = getButtonY();
        yes = addRenderableWidget(new ButtonBuilder(url == null ? CommonComponents.GUI_YES : CommonComponents.GUI_OPEN_IN_BROWSER, (s) -> {
            consumer.accept(true);
            onClose();
        })
                .setPosition(x, y).setSize(100, 20).build());
        no = addRenderableWidget(new ButtonBuilder(url == null ? CommonComponents.GUI_NO : CommonComponents.GUI_BACK, (s) -> {
            consumer.accept(false);
            onClose();
        })
                .setPosition(x+104, y).setSize(100, 20).build());
        if(url != null){
            copy = addRenderableWidget(new ButtonBuilder(CommonComponents.GUI_COPY_LINK_TO_CLIPBOARD, (s) -> {
                consumer.accept(false);
                assert this.minecraft != null;
                this.minecraft.keyboardHandler.setClipboard(url);
                onClose();
            })
                    .setPosition(x+29, y+24).setSize(150, 20).build());
        }
    }

    //#if MC >= 12002
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        super.renderBackground(guiGraphics, i, j, f);
        //#elseif MC < 12002
        //$$ public void renderBackground(GuiGraphics guiGraphics) {
        //$$         super.renderBackground(guiGraphics);
        //#endif
        int top = 0x00000000;
        int bottom = 0x7F000000;
        guiGraphics.fillGradient(0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight(), top, bottom);
    }
    protected int getButtonY(){
        int y = 70;
        if(icon != null) y+=55;
        if(!getTitle().getString().isBlank()) y+=25;
        for(FormattedCharSequence arg : getSplitText()) y+= (this.font.lineHeight+3);
        if(url != null) y+= (this.font.lineHeight+3);
        return Math.max(height/2-10, y);
    }
    protected List<FormattedCharSequence> getSplitText(){
        return this.font.split(message, Math.min(750, (int) (width*0.75)));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        //#if MC < 12002
        //$$     renderBackground(guiGraphics);
        //#endif
        super.render(guiGraphics, i, j, f);

        int y = 60;
        if(this.icon != null){
            guiGraphics.blit(
                    //#if MC >= 12106
                    RenderPipelines.GUI_TEXTURED,
                    //#elseif MC >= 12102
                    //$$ RenderType::guiTextured,
                    //#endif
                    icon, (width/2)-25, y, 0,0, 50,50, 50, 50);
            y+=55;
        }
        if(!getTitle().getString().isBlank()) {
            //#if MC <= 12105
            //$$ guiGraphics.pose().pushPose();
            //#else
            Matrix3x2f matrix3x2f = guiGraphics.pose().pushMatrix();
            //#endif
            guiGraphics.pose().scale(2.0F, 2.0F,
                    //#if MC <= 12105
                    //$$ 2.0F
                    //#else
                    matrix3x2f
                    //#endif
            );
            guiGraphics.drawCenteredString(this.font, this.title, this.width / 2 / 2, y / 2, -1);
            //#if MC <= 12105
            //$$ guiGraphics.pose().popPose();
            //#else
            guiGraphics.pose().popMatrix();
            //#endif
            y += 25;
        }
        for(FormattedCharSequence arg : getSplitText()){
            guiGraphics.drawCenteredString(this.font, arg, this.width / 2, y, -1);
            y+=(this.font.lineHeight+3);
        }
        if(url != null){
            y+=3;
            guiGraphics.drawCenteredString(this.font, Component.empty().setStyle(Style.EMPTY.withColor(0xFFbac2de)).append(url), this.width / 2, y, -1);
            y+=(this.font.lineHeight);
        }
    }

    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(parent);
    }
}
