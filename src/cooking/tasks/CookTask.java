package cooking.tasks;

import org.rspeer.commons.logging.Log;
import org.rspeer.game.adapter.component.inventory.Backpack;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.component.Inventories;
import org.rspeer.game.component.Production;
import org.rspeer.game.scene.Players;
import org.rspeer.game.scene.SceneObjects;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;

@TaskDescriptor(name = "Cooking the raw fish")
public class CookTask extends Task {

  @Override
  public boolean execute() {
    Backpack inv = Inventories.backpack();
    if (!inv.contains(iq -> iq.names("Raw shrimps").results())) {
      return false;
    }

    SceneObject fire = SceneObjects.query()
        .ids(43475)
        .results()
        .nearest();
    if (fire == null) {
      Log.warn("Make sure you start the script in Rogues den!");
      return false;
    }

    Player self = Players.self();
    if (self.isAnimating()) {
      sleepUntil(() -> !self.isAnimating(), () -> self.isAnimating(), 2);
      return false;
    }

    Production production = Production.getActive();
    if (production != null && production.isOpen()) {
      production.initiate(0);
    } else {
      inv.use(iq -> iq.names("Raw shrimps").results().first(), fire);
    }

    return true;
  }
}
