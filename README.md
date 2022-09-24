# Zygne-Architecture
### How to add:

Step 1. Add JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  Step 2. Add the dependency
  ```
  dependencies {
	        implementation 'com.github.ZygneApps:Zygne-Architecture:[version_code]'
	}
  ```

---
### Interactor
Step 1
Create a new intereactor ineterface which extends ``Interactor``
```
interface MainInteractor : Interactor {
    interface Callback {
        fun onMainCompleted()
    }
}
```
Step 2
Create an implementation of the newly created interactor interface. Make this implementation extend ``AbstractInteractor``. ``AbstractInteractor`` requires an ``Executor`` and a ``MainThread`` for running on a background thread and passing data to the main thread.

Override the method ``public void run()`` for the  intreactor, this method will be executed in a background thread.

When computation is done and you want to pass the information to the MainThread then simply call
```
mainThread.post { callback.onMainCompleted() }
```

```
class MainInteractorImpl(
    executor: Executor?, mainThread: MainThread?,
    private val callback: MainInteractor.Callback
) : AbstractInteractor(executor!!, mainThread!!), MainInteractor {
    override fun run() {
        // logic here
        mainThread.post { callback.onMainCompleted() }
    }
}
```
---
### Presentationn

#### Step 1
Create new presenter intereface which extends ``BasePresenter``

```
interface MainPresenter : BasePresenter {

    // View for this presenter
    interface View : BaseView {
        fun onMainCompleted()
    }

    fun start()
}
```

#### Step 2


```
class MainPresenterImpl(
        executor: Executor?, 
        mainThread: MainThread?,
        private var view: MainPresenter.View?) 
    : AbstractPresenter(executor!!, mainThread!!),
        MainPresenter {

    //...
}
```
Make the implementation of the presenter implement the callback of the interactor

```
class MainPresenterImpl(
        executor: Executor?,
        mainThread: MainThread?,
        private var view: MainPresenter.View?)
    : AbstractPresenter(executor!!, mainThread!!),
        MainPresenter,
        MainInteractor.Callback {


    override fun onMainCompleted() {
        // interactor has completed
        // update the view
    }

```
---
### Putting it all together
Make your Activity (or Fragment) implement the MainPresenter.View
```
public class MainActivity extends AppCompatActivity implements
        MainPresenter.View {

    // instance of the presenter
    private MainPresenter present
    //...
            
}
```
in the ``onCreate`` for your Activity initialize the presenter with the ``ThreadExecutor`` and the ``AndroidThread`` (both are singletons)
```
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(ThreadExecutor.getInstance(),
                AndroidThread.getInstance(),
                this);

    }
```
Call a presenter method from your Activity
```
presenter.start();
```
And the Architecture will do the computation  on a background thread and post the result to the ManiThread.

Please see the sample app for more information.