package com.hillel.artemjev.contactbook.menu.actions;

import com.hillel.artemjev.contactbook.menu.MenuAction;

public class ExitMenuAction implements MenuAction {

    @Override
    public void doAction() {
        System.out.println("\n*********************************");
        System.out.println("Всего хорошего)");
        System.out.println("*********************************");
    }

    @Override
    public String getName() {
        return "Выход";
    }

    @Override
    public boolean closeAfter() {
        return true;
    }
}
