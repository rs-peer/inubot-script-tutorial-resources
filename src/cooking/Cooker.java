package cooking;

import cooking.tasks.BankTask;
import cooking.tasks.CookTask;
import org.rspeer.commons.ArrayUtils;
import org.rspeer.commons.logging.Log;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskScript;
import org.rspeer.game.script.meta.ScriptMeta;

@ScriptMeta(
    name = "Cooker",
    developer = "Doga",
    version = 0.1,
    desc = "My first ever script. Totally!"
)
public class Cooker extends TaskScript {

  @Override
  public void initialize() {
    Log.info("Hello!");
  }

  @Override
  public void shutdown() {
    Log.info("Goodbye!");
  }

  @Override
  public Class<? extends Task>[] tasks() {
    return ArrayUtils.getTypeSafeArray(
        CookTask.class,
        BankTask.class
    );
  }
}