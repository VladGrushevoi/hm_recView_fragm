package com.vladgrushevoy.homework3_1

class DataHelper {
    private var ssd: MutableList<SSD> = ArrayList()

    init {
        ssd.run {
            add(SSD("Adata", "250 GB", R.drawable.adata))
            add(SSD("Crusial", "250 GB", R.drawable.crusial))
            add(SSD("Kingston", "500 GB", R.drawable.kingston_a2000))
            add(SSD("Kingston", "250 GB", R.drawable.kingston_grey))
            add(SSD("Samsung", "500 GB", R.drawable.samsung))
            add(SSD("Samsung", "1 TB", R.drawable.samsung_970))
            add(SSD("Samsung", "250 GB", R.drawable.samsung_evo_970))
            add(SSD("XPG", "120 GB", R.drawable.xpg))
            add(SSD("Adata", "250 GB", R.drawable.adata))
            add(SSD("Crusial", "250 GB", R.drawable.crusial))
            add(SSD("Kingston", "500 GB", R.drawable.kingston_a2000))
            add(SSD("Kingston", "250 GB", R.drawable.kingston_grey))
            add(SSD("Samsung", "500 GB", R.drawable.samsung))
            add(SSD("Samsung", "1 TB", R.drawable.samsung_970))
            add(SSD("Samsung", "250 GB", R.drawable.samsung_evo_970))
            add(SSD("XPG", "120 GB", R.drawable.xpg))
            add(SSD("Adata", "250 GB", R.drawable.adata))
            add(SSD("Crusial", "250 GB", R.drawable.crusial))
            add(SSD("Kingston", "500 GB", R.drawable.kingston_a2000))
            add(SSD("Kingston", "250 GB", R.drawable.kingston_grey))
            add(SSD("Samsung", "500 GB", R.drawable.samsung))
            add(SSD("Samsung", "1 TB", R.drawable.samsung_970))
            add(SSD("Samsung", "250 GB", R.drawable.samsung_evo_970))
            add(SSD("XPG", "120 GB", R.drawable.xpg))

        }
    }

    fun getProcessors() = ssd
}