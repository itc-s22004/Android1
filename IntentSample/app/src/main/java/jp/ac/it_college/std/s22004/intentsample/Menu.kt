package jp.ac.it_college.std.s22004.intentsample

data class Menu(val name:String, val price: Int)

val menuList = listOf(
    Menu("唐揚げ定食", 800),
    Menu("ハンバーグ定食",850),
    Menu("生姜焼き定食",850),
    Menu("ステーキ定食",700),
    Menu("野菜炒め定食",650),
    Menu("とんかつ定食",800),
    Menu("ミンチカツ定食",800),
    Menu("チキンカツ定食",800),
    Menu("コロッケ定食",750),
    Menu("回鍋肉定食",750),
    Menu("麻婆豆腐定食",750),
    Menu("八宝菜定食",750)
)