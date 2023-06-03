package tw.wsa.gaas.kotlin.emoji.generate

import tw.wsa.gaas.kotlin.emoji.generate.util.EmojiCollector
import tw.wsa.gaas.kotlin.emoji.generate.util.EmojiFileCreator
import tw.wsa.gaas.kotlin.emoji.generate.util.EmojiReader

fun main() {
    EmojiFileCreator.writeAsEnumFile(
        EmojiCollector.filter(
            EmojiReader.readTargetUrl("https://unicode.org/Public/emoji/15.0/emoji-test.txt")
        )
    )

    println("Done.")
}
