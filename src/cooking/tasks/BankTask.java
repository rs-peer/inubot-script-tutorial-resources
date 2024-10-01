package cooking.tasks;

import org.rspeer.game.adapter.component.inventory.Backpack;
import org.rspeer.game.adapter.component.inventory.Bank;
import org.rspeer.game.component.Inventories;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;

@TaskDescriptor(name = "Banking the cooked fish")
public class BankTask extends Task {

  @Override
  public boolean execute() {
    Backpack inv = Inventories.backpack();
    if (inv.contains(iq -> iq.names("Raw shrimps").results())) {
      return false;
    }

    if (!Bank.isOpen()) {
      Bank.open();
      return true;
    }

    Bank bank = Inventories.bank();
    bank.depositAllExcept(iq -> iq.names("Raw shrimps").results());
    bank.withdrawAll(iq -> iq.names("Raw shrimps").results().first());
    return true;
  }
}
