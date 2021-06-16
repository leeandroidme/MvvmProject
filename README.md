# MvvmProject
以前写的项目基本基于MVP框架进行开发，以前不是很喜欢MVVM，主要以前的思路就是XML中的数据关联字段name和代码中的字段name关联性太强，一旦出现改动出现错误调试可能ide不能很快定位错误
最近看到官网也在一直推MVVM项目架构，在网上查看了很多，感觉五花八门，有的基于databinding有的基于dababinding+livedata 
一直在想如果是dababinding，布局文件中的data指向的是viewmodel，viewmodel弹框等ui操作怎么弄，疑惑？
以及发起一个网络请求，线程切换flow 后续位置


于是开始写一个精简版基于谷歌官网推荐的架构搭建
采用的技术框架 ViewModel+Koin+LiveData+Retrifit2+Okhttp3+lifecycle+coroutines 这里只做主要的项目支撑，对于图片、json处理等一些自行选择
对于是否加入room根据自己项目需求自己配置了，好多简单项目可以不用数据库

这是Android官网推荐的项目架构
![MVVM架构图](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png?hl=zh_cn)

```
	  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0-native-mt'
    //---------------------------lifecycle viewmodel livedata----------------------------
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.3.0"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.0"

    //--------------------------------koin----------------------------
    implementation "org.koin:koin-android:$koinVersion"
    implementation "org.koin:koin-androidx-scope:$koinVersion"
    implementation "org.koin:koin-androidx-viewmodel:$koinVersion"

    //--------------------------------retrofit2 okhttp3----------------------------
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation 'com.squareup.okhttp3:okhttp:5.0.0-alpha.2'
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
```
具体参照可以参照register模块

Activity
```
class RegisterActivity : BaseActivity() {
    private val messageTv by lazy { findViewById<AppCompatTextView>(R.id.tv_message) }
    //通过koin注入viewmodel
    private val mViewModel by viewModel<RegisterViewModel>()
    override fun getLayoutId(): Int = R.layout.activity_register
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 这里通过livedata做双向绑定，当viewmodel数据发生变动时就会通知activty更新界面
        mViewModel.message.observe(this, {
            messageTv.setText(it)
            Log.e("mesa=====", it)
        })
         mViewModel.register("sdf", "sdf", "sdf")
    }
}
```
ViewModel
```
class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {
    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun register(username: String, password: String, repass: String) {
        viewModelScope.launch {
            flow {
                emit(repository.register(username, password, repass))
            }.catch {
                message.value = it.message
            }.onStart {
                message.value = "注册开始了。。。"
            }.onCompletion {
                message.value = "注册完成了。。。"
            }.collectLatest {
                message.value = it.message()
            }
        }
    }
}
```
RegisterRepository
```
class RegisterRepository(private val api: ApiService) {//ApiService基于Retrofit2 OkHttp3实现的网络请求接口
    suspend fun register(username: String, password: String, repass: String) =
        withContext(Dispatchers.IO) {
            api.register(username, password, repass)
        }
}
```

根据上面的分成是不是特别简单，既实现了data——view的双向绑定也避免了databingding造成的困扰

