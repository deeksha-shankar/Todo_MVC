package com.org.ToDo.Testcases;

import com.org.ToDo.Base;
import com.org.ToDo.Methods;
import org.testng.annotations.Test;

public class TestCase extends Base {

    @Test
    public void Scenarios () throws InterruptedException{

        Methods con = new Methods();
        String item = "Pack my bags";

        //Test Scenario 1 - Add items
        con.addItem(item);

        //Test Scenario 4 - Filter Scenarios
        con.clickActive();
        con.clickCompleted();

        //Test Scenario 2 - Mark Complete
        con.markComplete(item);

        //Test Scenario 4 - Filter Scenarios
        con.clickActive();
        con.clickCompleted();

        //Test Scenario 3 - Delete Item
        con.delItem(item);

    }
}
