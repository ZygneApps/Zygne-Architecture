# Zygne-Architecture
How to add:

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
	        implementation 'com.github.ZygneApps:Zygne-Architecture:1.0.1'
	}
  ```

---
### Interactor
Step 1
Create a new intereactor ineterface which extends ``Interactor``
```
public interface MainInteractor extends Interactor {

    // Callback interface for passing result to listener
    interface Callback {
        // this intereactor has completed
        void onMainCompleted();
    }
}
```
Step 2
Create an inplementation of the newely created interactor interface. Make this implementation extend ``AbstractInteractor``. ``AbstractInteractor`` requires an ``Executor`` and a ``MainThread`` for running on a background thread and passing data to the main thread.

Override the method ``public void run()`` for the  intreactor, this method will be executed in a background thread.

When computation is done and you want to pass the information to the MainThread then simply call
```
mainThread.post(()-> callback.onMainCompleted());
```

Sample class
```
public class MainInteractorImpl extends AbstractInteractor implements
        MainInteractor {

    // instance of callback for passing updates
    private Callback callback;

    public MainInteractorImpl(Executor executor, MainThread mainThread, Callback callback) {
        super(executor, mainThread);
        this.callback = callback;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainThread.post(()-> callback.onMainCompleted());
    }
}
```
---
### Presentationn

#### Step 1
Create new presenter intereface which extends ``BasePresenter``

```
public interface MainPresenter extends BasePresenter {

    // View for this presenter
    // Base view contains common preseentation methods such as show and hide progress
    interface View extends BaseView {
        void onMainCompleted();
    }
    
    // start the presenter
    void start();
}
```

#### Step 2


```
public class MainPresenterImpl extends AbstractPresenter implements
        MainPresenter,
        MainInteractor.Callback {

    private View view;

    public MainPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);

        this.view = view;
    }

    @Override
    public void onMainCompleted() {
        if(view != null){
            view.hideProgress();
            view.onMainCompleted();
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        view = null;
    }

    @Override
    public void start() {
        if(view != null){
            view.showProgress();

            MainInteractor interactor =
                    new MainInteractorImpl(executor, mainThread, this);

            interactor.execute();
        }
    }
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
Call a presennter method from your Activity
```
presenter.start();
```
And the Architecture will do the computation  on a background thread and post the result to the ManiThread.

Please see the sample app for more information.