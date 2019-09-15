package com.arrggh.mudworld.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CommandBuilder {
    interface ICommandFragment {
        boolean match(String chunk);

        void process(List<String> arguments, String c);
    }

    static class CommandFragment implements ICommandFragment {
        String text;

        public boolean match(String chunk) {
            return text.equals(chunk) || text.startsWith(chunk);
        }

        @Override
        public void process(List<String> arguments, String c) {
            // Command doesn't add any arguments
        }

        @Override
        public String toString() {
            return text;
        }
    }

    static abstract class TargetFragment implements ICommandFragment {
        public abstract boolean match(String chunk);

        @Override
        public void process(List<String> arguments, String c) {
            arguments.add(c);
        }

        protected boolean _match(String chunk) {
            int count = 0;
            String target = chunk;
            if (chunk.contains(".")) {
                String[] c = chunk.split("\\.");
                count = Integer.valueOf(c[0]);
                target = c[1];
            }
            return true;
        }
    }

    static class OptionalTargetFragment extends TargetFragment {
        @Override
        public String toString() {
            return "<TARGET>";
        }

        @Override
        public boolean match(String chunk) {
            if (chunk == null || chunk.isEmpty()) {
                return true;
            }
            return _match(chunk);
        }
    }

    static class MandatoryTargetFragment extends TargetFragment {
        @Override
        public String toString() {
            return "[TARGET]";
        }

        @Override
        public boolean match(String chunk) {
            if (chunk == null || chunk.isEmpty()) {
                return false;
            }
            return _match(chunk);
        }
    }

    static class Command {
        List<ICommandFragment> fragments = new LinkedList<>();
        Class<?> commandToExecute;

        @Override
        public String toString() {
            return fragments.toString();
        }

        public List<String> match(String[] chunks) {
            List<String> arguments = new LinkedList<>();
            for (int i = 0; i != Math.max(chunks.length, fragments.size()); i++) {
                if (i >= fragments.size()) {
                    System.out.println("More chunks than fragments");
                    return null;
                }
                if (i < chunks.length) {
                    ICommandFragment f = fragments.get(i);
                    String c = chunks[i];
                    if (!f.match(c)) {
                        return null;
                    } else {
                        f.process(arguments, c);
                    }
                }
            }
            return arguments;
        }
    }

    static class Result {
        Class<?> command;
        List<String> arguments;

        @Override
        public String toString() {
            return command.getName().toString() + " " + arguments;
        }
    }

    static class CommandParser {
        List<Command> commands = new LinkedList<>();

        Result parse(String text) {
            System.out.println("Parsing " + text);
            String[] chunks = text.split("\\s+");

            List<Command> potientials = new ArrayList<>(commands);


            Iterator<Command> iterator = potientials.iterator();

            Set<Result> results = new HashSet<>();

            while (iterator.hasNext()) {
                Command command = iterator.next();
                List<String> args = command.match(chunks);
                if (args == null) {
                    iterator.remove();
                } else {
                    Result r = new Result();
                    r.command = command.commandToExecute;
                    r.arguments = args;
                    results.add(r);
                }
            }

            if (results.size() == 1) {
                return results.iterator().next();
            }

            Result r = new Result();
            r.command = UnknownCommand.class;
            r.arguments = null;
            return r;
        }
    }

    private static final Map<Class<?>, Object> instances = new HashMap<>();

    public static String execute(Result result) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (!instances.containsKey(result.command)) {
            instances.put(result.command, result.command.getDeclaredConstructor().newInstance());
        }
        Object instance = instances.get(result.command);

        System.out.println("Searching for method with arguments: " + result.arguments);
        Method method;
        if (result.arguments == null) {
            System.out.println("Empty method");
            method = result.command.getMethod("perform");
            method.invoke(instance);
        } else {
            Class<?>[] types = new Class<?>[result.arguments.size()];
            for (int i = 0; i != types.length; i++) {
                types[i] = result.arguments.get(i).getClass();
            }
            System.out.println("Method with " + Arrays.toString(types));
            method = result.command.getMethod("perform", types);
            method.invoke(instance, result.arguments);
        }


        return "Done";
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Command> commands = new LinkedList<>();

        InputStream stream = CommandBuilder.class.getResourceAsStream("/commands.dat");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = reader.readLine();
        while (line != null) {
            String chunks[] = line.split("\\s+");

            Command cmd = new Command();

            boolean command = true;
            for (String chunk : chunks) {
                if (!command) {
                    cmd.commandToExecute = Class.forName("com.arrggh.mudworld.commands." + chunk + "Command");
                } else if (chunk.equals("<TARGET>")) {
                    cmd.fragments.add(new OptionalTargetFragment());
                } else if (chunk.equals("[TARGET]")) {
                    cmd.fragments.add(new MandatoryTargetFragment());
                } else if (chunk.equals("|")) {
                    command = false;
                } else {
                    CommandFragment cf = new CommandFragment();
                    cf.text = chunk;
                    cmd.fragments.add(cf);
                }
            }
            commands.add(cmd);

            line = reader.readLine();
        }

        CommandParser parser = new CommandParser();
        parser.commands = commands;

        System.out.println("EXEC 1: " + execute(parser.parse("n")));
        System.out.println("EXEC 2: " + execute(parser.parse("no")));
        System.out.println("EXEC 3: " + execute(parser.parse("nor")));
        System.out.println("EXEC 4: " + execute(parser.parse("nort")));
        System.out.println("EXEC 5: " + execute(parser.parse("north")));
        System.out.println("EXEC 6: " + execute(parser.parse("north door")));
        System.out.println("EXEC 7: " + execute(parser.parse("north 3.door")));
        System.out.println("EXEC 8: " + execute(parser.parse("put dagger in 3.door")));
        System.out.println("EXEC 9: " + execute(parser.parse("put boot")));
        System.out.println("EXEC 10: " + execute(parser.parse("p 4.b d")));
    }
}
