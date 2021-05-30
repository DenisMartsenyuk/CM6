import ru.lab.lab6.functions.Function;
import ru.lab.lab6.functions.FunctionFirst;
import ru.lab.lab6.utils.ReaderInputData;
import ru.lab.lab6.utils.Solver;

import java.util.ArrayList;
import java.util.List;

public class Application {
    Solver solver;
    List<Function> functions;

    public Application() {
        solver = new Solver();
        functions = new ArrayList<>();
        functions.add(new FunctionFirst());
    }

    public void start() {
        solver.solve(ReaderInputData.read(functions));
    }
}
