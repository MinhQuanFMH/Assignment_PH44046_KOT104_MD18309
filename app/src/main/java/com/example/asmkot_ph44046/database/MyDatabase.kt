package com.example.asmkot_ph44046.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.asmkot_ph44046.database.dao.CartDao
import com.example.asmkot_ph44046.database.dao.ProductDao
import com.example.asmkot_ph44046.database.dao.SaveUserDao
import com.example.asmkot_ph44046.database.dao.UserDao
import com.example.asmkot_ph44046.database.entities.Carts
import com.example.asmkot_ph44046.database.entities.Products
import com.example.asmkot_ph44046.database.entities.SaveUsers
import com.example.asmkot_ph44046.database.entities.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Users::class, Products::class, Carts::class, SaveUsers::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun saveUserDao(): SaveUserDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "item_database"
                ).addCallback(ProductDatabaseCallback(scope))
                    .build()
                    .also { INSTANCE = it }
            }
        }

        class ProductDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(productDao = database.productDao())
                    }
                }
            }

            suspend fun populateDatabase(productDao: ProductDao) {
                val products1 = Products(
                    id = 1,
                    "Sản phẩm 1",
                    "https://i.pinimg.com/564x/65/cb/69/65cb69e16a5fc9b443f43dfa6757b1d0.jpg",
                    5,
                    2
                )
                val products2 = Products(
                    id = 2,
                    "Sản phẩm 2",
                    "https://i.pinimg.com/564x/d1/c7/78/d1c778f97adf09b3b893fa8e0d7c47c8.jpg",
                    8,
                    5
                )
                val products3 = Products(
                    id = 3,
                    "Sản phẩm 3",
                    "https://i.pinimg.com/564x/0c/f4/a4/0cf4a47dedca2f369d5bf604a350d2cf.jpg",
                    6,
                    4
                )
                val products4 = Products(
                    id = 4,
                    "Sản phẩm 4",
                    "https://i.pinimg.com/564x/fd/b8/df/fdb8df975b154aad3e3084453a7056ab.jpg",
                    5,
                    1
                )
                val products5 = Products(
                    id = 5,
                    "Sản phẩm 5",
                    "https://i.pinimg.com/564x/11/ed/a3/11eda3b866166f6a2ab8a8e1c47ac704.jpg",
                    10,
                    2
                )
                val products6 = Products(
                    id = 6,
                    "Sản phẩm 6",
                    "https://i.pinimg.com/564x/4a/5f/17/4a5f17d3b226500161074a7f3512ea6d.jpg",
                    8,
                    3
                )
                productDao.addProduct(products1)
                productDao.addProduct(products2)
                productDao.addProduct(products3)
                productDao.addProduct(products4)
                productDao.addProduct(products5)
                productDao.addProduct(products6)
            }
        }
    }
}