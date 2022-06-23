package com.example.pal;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.pal.fragments.create.CreateBottomDialog;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestPal {
    @Test
    public void testCheckName() {

        CreateBottomDialog testDialog = new CreateBottomDialog();
        //объявление вводимых данных для теста
        //правильное имя файла
        String nameRight = "ИВАН";
        //неправильное имя файла
        String nameBad = "/EEEE)";

        //если имя правильное, то должно вернутся true
        assertEquals(true, testDialog.checkName(nameRight));
        // если не правльное - false
        assertEquals(false, testDialog.checkName(nameBad));
    }
}