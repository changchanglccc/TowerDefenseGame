package testingUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
    BankTest.class,
    BurningTowerShootingBehaviorTest.class,
    CoordinateConverterTest.class,
    CritterTest.class,
    EntranceExitPointTest.class,
    GameLogTest.class,
    IceTowerShootingBehaviorTest.class,
    SavingLoadingGameTest.class,
    SplashTowerShootingBehaviorTest.class,
    TargetBasedOnClosestToTowerTest.class,
    TargetBasedOnNearestTest.class,
    TargetBasedOnStrongestTest.class,
    TargetBasedOnWeakestTest.class,
    TestingMapTest.class,
    TowerFactoryTest.class,
    UtilityclassTest.class,
    ValidateMapTest.class })
public class AllTests {

}
