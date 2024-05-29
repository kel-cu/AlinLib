package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.Util;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.AlinLibTest;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.ImageWidget;
import ru.kelcuprum.alinlib.gui.components.builder.button.*;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.slider.*;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.MessageBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

public class AlinaDemoScreen {
    private static final ResourceLocation icon = new ResourceLocation(AlinLibTest.MODID, "textures/gui/widget/test/normal.png");
    private static final ResourceLocation ImFine = new ResourceLocation(AlinLibTest.MODID, "textures/gui/widget/test/imfine.png");
    private static final String[] list = {
            "Welcome", "to", "The", "Amazing", "Digital", "Interface", "™"
    };

    public Screen build(Screen parent) {
        ConfigScreenBuilder screen = new ConfigScreenBuilder(parent)
                .setTitle(Component.translatable("alinlibtest.name"))
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #0"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #1"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #2"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #3"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #4"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #5"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #6"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #7"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #8"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #9"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())

                .addWidget(new TextBox(Component.literal("TextBox"), true)
                        .setDescription(Component.literal("Hello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\nHello, world!\n")))
                .addWidget(new ButtonBuilder(Component.literal("ButtonBuilder"), (s) -> new ToastBuilder().setIcon(Items.SALMON).setTitle(Component.literal("AlinLib")).setMessage(Component.literal("Good morning, Mr. Sunfish!")).show(AlinLib.MINECRAFT.getToasts())).build())
                .addWidget(new ButtonBooleanBuilder(Component.literal("ButtonBooleanBuilder"), true).build())
                .addWidget(new ButtonSpriteBuilder(icon).setSize(20, 20).build())
                .addWidget(new EditBoxBuilder(Component.literal("EditBoxBuilder")).build())
                .addWidget(new MessageBox(Component.literal("Hello, world")))
                .addWidget(new SliderDoubleBuilder(Component.literal("SliderDoubleBuilder")).build())
                .addWidget(new SliderFloatBuilder(Component.literal("SliderFloatBuilder")).build())
                .addWidget(new EditBoxBuilder(Component.literal("EditBoxBuilder")).build())
                .addWidget(new MessageBox(Component.literal("Блять, да мне похуй на тебя, блять, слушай, какая у тебя там тачка, блять, квартиры, срачки там блять, яхты, всё, мне похуй, хоть там \"Бэнтли\", хоть блять нахуй \"Майбах\", хоть \"Роллс-Ройс\", хоть \"Бугатти\" блять, хоть стометровая яхта, мне на это насрать, понимаешь? Сколько ты там, кого ебешь, каких баб, каких значит вот этих самок шикарных или атласных, блять в космос ты летишь, мне на это насрать, понимаешь?"), true, (s) -> Util.getPlatform().openUri("https://www.youtube.com/watch?v=50nlHgRYp1I")))
                .addWidget(new MessageBox(Component.literal("Я, блять, в своем познании настолько преисполнился, что я как будто бы уже сто триллионов миллиардов лет, блять, проживаю на триллионах и триллионах таких же планет, понимаешь, как эта Земля, мне уже этот мир абсолютно понятен, и я здесь ищу только одного, блять, - покоя, умиротворения и вот этой гармонии, от слияния с бесконечно вечным, от созерцания этого великого фрактального подобия и от вот этого замечательного всеединства существа, бесконечно вечного, куда ни посмотри, хоть вглубь - в бесконечно малое, хоть ввысь - бесконечно большое, понимаешь? А ты мне опять со своим вот этим блять, иди суетись дальше, это твоё распределение, это твой путь и твой горизонт познания и ощущения твоей природы, он несоизмеримо мелок по сравнению с моим, понимаешь? Я как будто бы уже давно глубокий старец, бессмертный, ну или там уже почти бессмертный, который на этой планете от её самого зарождения, ещё когда только Солнце только-только сформировалось как звезда, и вот это газопылевое облако, вот, после взрыва, Солнца, когда оно вспыхнуло, как звезда, начало формировать вот эти коацерваты, планеты, понимаешь, я на этой Земле уже как будто почти пять миллиардов лет блять живу и знаю её вдоль и поперёк этот весь мир, а ты мне какие-то..."), (s) -> Util.getPlatform().openUri("https://youtu.be/50nlHgRYp1I?si=xmAOgU0kytoz_hkB&t=26")))
                .addWidget(new MessageBox(Component.literal("Мне похуй на твои тачки, на твои блять нахуй яхты, на твои квартиры, там, на твоё благо. Я был на этой планете бесконечным множеством, и круче Цезаря, и круче Гитлера, и круче всех великих, понимаешь, был, а где-то был конченым говном, ещё хуже, чем здесь. Я множество этих состояний чувствую. Где-то я был больше подобен растению, где-то я больше был подобен птице, там, червю, где-то был просто сгусток камня, это всё есть душа, понимаешь? Она имеет грани подобия совершенно многообразные, бесконечное множество. Но тебе этого не понять, поэтому ты езжай себе блять."), (s) -> Util.getPlatform().openUri("https://youtu.be/50nlHgRYp1I?si=xUcI73UGg1TWsmm1&t=105")))
                .addWidget(new MessageBox(Component.literal("Мы в этом мире как бы живем разными ощущениями и разными стремлениями, соответственно, разное наше и место, разное и наше распределение. Тебе я желаю все самые крутые тачки чтоб были у тебя, и все самые лучше самки чтобы раздвигали ноги перед тобой, чтобы раздвигали перед тобой щели, на шиворот-навыворот, блять, перед тобой, как ковёр, это самое, раскрывали, растлевали, растлали, и ты их чтобы ебал до посинения, докрасна, вон, как Солнце закатное, и чтоб на лучших яхтах, и на самолётах летал, и кончал прямо с иллюминатора, и делал всё, что только в голову могло прийти и не прийти, если мало идей, обращайся ко мне, я тебе на каждую твою идею предложу сотню триллионов, как всё делать."), (s) -> Util.getPlatform().openUri("https://youtu.be/50nlHgRYp1I?si=_t069cp-Rvvqok18&t=142")))
                .addWidget(new MessageBox(Component.literal("Ну а я всё, я иду как глубокий старец, узревший вечное, прикоснувшийся к Божественному, сам стал богоподобен и устремлен в это бесконечное, и который в умиротворении, покое, гармонии, благодати, в этом сокровенном блаженстве пребывает, вовлеченный во всё и во вся, понимаешь, вот и всё, в этом наша разница. Так что я иду любоваться мирозданием, а ты идёшь преисполняться в гранях каких-то, вот и вся разница, понимаешь, ты не зришь это вечное бесконечное, оно тебе не нужно. Ну зато ты, так сказать, более активен, как вот этот дятел долбящий, или муравей, который очень активен в своей стезе, поэтому давай, наши пути здесь, конечно, имеют грани подобия, потому что всё едино, но я-то тебя прекрасно понимаю, а вот ты меня - вряд ли, потому что я как бы тебя в себе содержу, всю твою природу, она составляет одну маленькую там песчиночку, от того что есть во мне, вот и всё, поэтому давай, ступай, езжай, а я пошел наслаждаться нахуй блять прекрасным осенним закатом на берегу теплой южной реки. Всё, пиздуй-бороздуй, и я попиздил, нахуй."), (s) -> Util.getPlatform().openUri("https://youtu.be/50nlHgRYp1I?si=ZixPeqoZcwUT1-cH&t=187")))
                .addWidget(new SliderIntegerBuilder(Component.literal("SliderIntegerBuilder")).build())
                .addWidget(new SliderPercentBuilder(Component.literal("SliderPercentBuilder")).build())
                .addWidget(new CategoryBox(Component.literal("CategoryBox"))
                        .addValue(new ImageWidget(0, 0, InterfaceUtils.DEFAULT_WIDTH(), InterfaceUtils.DEFAULT_WIDTH(), ImFine, 20 ,20, Component.empty())));
        return screen.build();
    }
}
