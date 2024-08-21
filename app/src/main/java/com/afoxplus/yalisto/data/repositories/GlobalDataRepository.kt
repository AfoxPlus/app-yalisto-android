package com.afoxplus.yalisto.data.repositories

import com.afoxplus.yalisto.domain.repositories.GlobalRepository
import javax.inject.Inject

class GlobalDataRepository @Inject constructor() : GlobalRepository {
    //Get data from service
    override fun getCurrencyID(): String = "61a18be00b6de1476436de41"
}