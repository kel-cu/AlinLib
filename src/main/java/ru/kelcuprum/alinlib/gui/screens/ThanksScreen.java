package ru.kelcuprum.alinlib.gui.screens;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.WebAPI;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.text.MessageBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;

import java.awt.*;

import static java.lang.Integer.parseInt;
import static ru.kelcuprum.alinlib.gui.Icons.EXIT;
import static ru.kelcuprum.alinlib.gui.Icons.THANKS;

public class ThanksScreen extends Screen {
    public final Screen parent;
    public JsonArray dobryaki = selfThanks();
    public ThanksScreen(Screen parent) {
        super(Component.translatable("alinlib.thanks"));
        this.parent = parent;
        try{
            JsonObject res = WebAPI.getJsonObject("https://api.kelcuprum.ru/thanks");
            if(res.has("error")){
                this.dobryaki = selfThanks();
            } else this.dobryaki = res.getAsJsonArray("pwgood3");
        } catch (Exception ex){
            this.dobryaki = selfThanks();
        }
    }
    protected JsonArray selfThanks(){
        JsonArray oi = new JsonArray();

        JsonObject clownfish = new JsonObject();
        clownfish.addProperty("name", "Clownfish");
        oi.add(clownfish);

        JsonObject tetra = new JsonObject();
        tetra.addProperty("name", "Tetra");
        oi.add(tetra);

        JsonObject seadrive = new JsonObject();
        seadrive.addProperty("name", "SeaDrive");
        oi.add(seadrive);

        JsonObject groupie = new JsonObject();
        groupie.addProperty("name", "Groupie");
        oi.add(groupie);

        JsonObject alina = new JsonObject();
        alina.addProperty("name", "Alina Kotova");
        oi.add(alina);
        return oi;
    }

    protected TextBox pwgood3;
    protected MessageBox offer;

    @Override
    protected void init() {
        addRenderableWidget(new TextBox(width/2-200, 10, 400, 20, Component.translatable("alinlib.thanks"), true));
        pwgood3 = addRenderableWidget( new TextBox(width/2-200, 35, 400, AlinLib.MINECRAFT.font.lineHeight, getDobryak(), true));
        offer = addRenderableWidget(new MessageBox(width/2-200, (40+AlinLib.MINECRAFT.font.lineHeight), 400, 100, Component.translatable("alinlib.thanks.offer"), true));
        addRenderableWidget(new ButtonBuilder(Component.translatable("alinlib.thanks.boosty"), (s) -> Util.getPlatform().openUri("https://kelcu.ru/boo")).setWidth(150).setPosition(width-155, height-75).build());
        addRenderableWidget(new ButtonBuilder(Component.translatable("alinlib.thanks.donationalerts"), (s) -> Util.getPlatform().openUri("https://www.donationalerts.com/r/kel_cu")).setWidth(150).setPosition(width-155, height-50).build());
        addRenderableWidget(new ButtonBuilder(Component.translatable("alinlib.thanks.exit"), (s) -> onClose()).setIcon(EXIT).setWidth(150).setPosition(width-155, height-25).build());
    }
    long stime = System.currentTimeMillis();
    int pos = 0;
    @Override
    public void tick() {
        super.tick();
        long time = System.currentTimeMillis() - stime;
        if(time > 3000){
            stime = System.currentTimeMillis();
            pos++;
            if(pos >= dobryaki.size()) pos = 0;
            pwgood3.setMessage(getDobryak());
        }
    }
    public Component getDobryak(){
        Component dobryak = Component.empty();
        if(dobryaki.get(pos).isJsonObject()) {
            MutableComponent coloredDobryak = Component.empty();
            JsonObject jsonDobryaka = dobryaki.get(pos).getAsJsonObject();
            if(jsonDobryaka.has("color")) coloredDobryak.setStyle(Style.EMPTY.withColor(Color.decode(jsonDobryaka.get("color").getAsString()).getRGB()));
            coloredDobryak.append(jsonDobryaka.get("name").getAsString());
            dobryak = coloredDobryak;
        } else dobryak = Component.literal(dobryaki.get(pos).getAsString());
        return dobryak;
    }

    @Override
    //#if MC >=12002
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        super.renderBackground(guiGraphics, i, j, f);
        //#elseif MC < 12002
        //$$ public void renderBackground(GuiGraphics guiGraphics) {
        //$$         super.renderBackground(guiGraphics);
        //#endif
        int x = 0;
        int y = height-150;
        guiGraphics.blit(
                //#if MC >= 12102
                RenderType::guiTextured,
                //#endif
                THANKS, x, y, 0f, 0f, 150, 150, 150, 150);
    }
    //#if MC < 12002
    //$$ @Override
    //$$ public void render(GuiGraphics guiGraphics, int i, int j, float f) {
    //$$     renderBackground(guiGraphics);
    //$$     super.render(guiGraphics, i, j, f);
    //$$ }
    //#endif


    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(keyCode == GLFW.GLFW_KEY_D && (modifiers & GLFW.GLFW_MOD_SHIFT) != 0)
            AlinLib.MINECRAFT.setScreen(parent);
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(parent);
    }
}
