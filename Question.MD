## FAQs
> 自问自答

- ViewModelProvider
为什么用这个创建ViewModel?
Activity/Fragment 是ViewModelStoreOwner，通过ViewModelProvider获取到ViewModelStoreOwner的
ViewModelStore，从mViewModelStores获取ViewModel。如果没有则创建一个ViewModel并加入ViewModelStore

- 带参数的ViewModel怎么创建？
通过ViewModelProvider.Factory

- databinding双向绑定是什么意思
简单的说，就是：Data变化，View做出响应。View变化，Data也变化。
一般来说，View随着Data变化，就够了，也就是单项绑定。
[参考](!https://www.jianshu.com/p/e8b6ba90de53)