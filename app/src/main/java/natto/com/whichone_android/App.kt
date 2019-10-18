package natto.com.whichone_android

import android.app.Application
import natto.com.whichone_android.api.firebase.FirebaseApi
import natto.com.whichone_android.api.firebase.impl.FirebaseApiImpl
import natto.com.whichone_android.common.ConnectListener
import natto.com.whichone_android.domain.repository.FireStoreRepository
import natto.com.whichone_android.domain.repository.impl.FireStoreRepositoryImpl
import natto.com.whichone_android.ui.main.MainViewModel
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
            this, listOf(
                this.moduleCommon,
                this.moduleFirebase,
                this.moduleMain
            )
        )
    }

    private val moduleFirebase = module {
        factory {
            FirebaseApiImpl(
                connectListener = get()
            ) as FirebaseApi
        }
        factory {
            FireStoreRepositoryImpl(
                firebaseApi = get()
            ) as FireStoreRepository
        }
    }

    private val moduleMain = module {
        factory {
            MainViewModel(
                application = this@App,
                fireStoreRepository = get()
            )
        }
    }

    private val moduleCommon = module {
        factory {
            ConnectListener(
                context = this@App
            )
        }
    }
}