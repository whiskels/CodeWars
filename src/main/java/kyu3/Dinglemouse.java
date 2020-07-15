package main.java.kyu3;

/**
 * 3 kyu - The lift
 *
 * https://www.codewars.com/kata/58905bfa1decb981da00009e
 *
 * Details:
 *
 * Synopsis
 *
 * A multi-floor building has a Lift in it.
 *
 * People are queued on different floors waiting for the Lift.
 *
 * Some people want to go up. Some people want to go down.
 *
 * The floor they want to go to is represented by a number
 * (i.e. when they enter the Lift this is the button they will press)
 *
 * Rules
 * Lift Rules
 *
 *     The Lift only goes up or down!
 *     Each floor has both UP and DOWN Lift-call buttons (except top and ground floors which have
 *     only DOWN and UP respectively)
 *     The Lift never changes direction until there are no more people wanting to get on/off in the
 *     direction it is already travelling
 *     When empty the Lift tries to be smart. For example,
 *         If it was going up then it may continue up to collect the highest floor person wanting
 *         to go down
 *         If it was going down then it may continue down to collect the lowest floor person
 *         wanting to go up
 *     The Lift has a maximum capacity of people
 *     When called, the Lift will stop at a floor even if it is full, although unless somebody gets
 *     off nobody else can get on!
 *     If the lift is empty, and no people are waiting, then it will return to the ground floor
 *
 * People Rules
 *
 *     People are in "queues" that represent their order of arrival to wait for the Lift
 *     All people can press the UP/DOWN Lift-call buttons
 *     Only people going the same direction as the Lift may enter it
 *     Entry is according to the "queue" order, but those unable to enter do not block those behind
 *     them that can
 *     If a person is unable to enter a full Lift, they will press the UP/DOWN Lift-call button
 *     again after it has departed without them
 *
 *     Kata Task
 *
 *     Get all the people to the floors they want to go to while obeying the Lift rules and the
 *     People rules
 *     Return a list of all floors that the Lift stopped at (in the order visited!)
 *
 * NOTE: The Lift always starts on the ground floor (and people waiting on the ground floor may
 * enter immediately)
 */

import java.util.*;
import java.util.stream.IntStream;

public class Dinglemouse {
    private final int capacity;                 // Lift capacity
    private final Map<Integer, List> queues;    // Current queues
    private boolean isFinished;                 // Is everyone at desired floor
    private Direction direction;                // Current travel direction
    private List<Passenger> peopleInside;       // List of people inside lift
    private List<Integer> visitedFloors;        // Floors that lift has visited
    private int currentFloor, limitFloor;       // Floor variables (current, max next)

    private static enum Direction {UP, DOWN;}

    /* Additional class, representing each passenger standing in a queue */
    class Passenger {
        private final int desiredFloor;
        private final Direction direction;

        public Passenger(int startFloor, int desiredFloor) {
            this.desiredFloor = desiredFloor;
            this.direction = desiredFloor > startFloor ? Direction.UP : Direction.DOWN;
        }

        public final String toString() {
            return String.format("From floor to %d (%s)", desiredFloor, direction);
        }
    }

    /* Constructor is used to avoid using static members; */
    private Dinglemouse(int[][] queues, final int capacity) {
        this.capacity = capacity;
        this.queues = new HashMap<>(queues.length);
        fillQueues(queues);
        peopleInside = new ArrayList<>(capacity);
        direction = Direction.UP;
        currentFloor = 0;
        visitedFloors = new ArrayList<>();
        isFinished = false;

        printQueue();                                                       // Print initial state
        msg(String.format("Lift:\n\t" +
                "Lift capacity: %d\n\t" +
                "Total number of floors: %d", capacity, queues.length));
    }

    // Starting point
    public static int[] theLift(final int[][] queues, final int capacity) {
        Dinglemouse lift = new Dinglemouse(queues, capacity);
        lift.work();
        return lift.getVisitedFloors();
    }

    /* Prints messages */
    public static void msg(String line) {
        System.out.println(line);
    }

    /* Used to fill queues map with initial values */
    private void fillQueues(int[][] queues) {
        for (int i = 0; i < queues.length; i++) {
            this.queues.put(i, new ArrayList<>(queues[i].length));
            for (int j = 0; j < queues[i].length; j++) {
                this.queues.get(i).add(new Passenger(i, queues[i][j]));
            }
        }
    }

    /* Main method with lift logic */
    private void work() {
        visitedFloors.add(currentFloor);
        recalculateLimitFloor();

        while (!isFinished) {
            if (checkFloor()) {
                msg(String.format("Stopped at floor %d", currentFloor));
                dropPassengers();
                pickPassengers();

                if (visitedFloors.get(visitedFloors.size() - 1) != currentFloor) {
                    visitedFloors.add(currentFloor);
                }
            } else {
                msg(String.format("Skipping floor %d", currentFloor));
            }

            if (currentFloor == limitFloor) {
                msg("Reached max floor");
                switchDirection();
            } else {
                changeFloor();
            }
            isFinished = isNotFinished();
        }

        msg("Everyone is on desired floors, returning to ground floor");
        currentFloor = 0;

        if (visitedFloors.get(visitedFloors.size() - 1) != 0) {
            visitedFloors.add(0);
        }
    }

    /* Proceeds to next floor */
    private void changeFloor() {
        currentFloor += direction == Direction.UP ? 1 : -1;
    }

    /* Checks if current floor is target floor for any of the passengers or anyone can enter lift */
    private boolean checkFloor() {
        boolean isAnyoneDesired = peopleInside.size() == 0 ? false : peopleInside.stream()
                .anyMatch(passenger -> passenger.desiredFloor == currentFloor);

        boolean isAnyoneToEnter = queues.get(currentFloor).size() == 0 ? false : queues.get(currentFloor).stream()
                .anyMatch(passenger -> isSameDirection((Passenger) passenger));

        return isAnyoneDesired || isAnyoneToEnter;
    }

    /* Inverts lift's direction */
    private void switchDirection() {
        direction = direction == Direction.UP ? Direction.DOWN : Direction.UP;
        recalculateLimitFloor();
    }

    /* Updates limit floor */
    private void recalculateLimitFloor() {
        limitFloor = direction == Direction.UP ? getMaxNextFloor() : getMinNextFloor();
        msg(String.format("Max floor %d", limitFloor));
    }

    private int getMaxNextFloor() {
        return Math.max(maxNextFloorForPassengers(), maxNextFloorWithPassengers());
    }

    private int getMinNextFloor() {
        return Math.min(minNextFloorForPassengers(), minNextFloorWithPassengers());
    }

    /* Calculates maximum floor with passengers in queue */
    private int maxNextFloorWithPassengers() {
        for (int i = queues.size() - 1; i > currentFloor; i--) {
            if (queues.get(i).size() != 0) {
                return i;
            }
        }
        return currentFloor;
    }

    /* Calculates minimum floor with passengers in queue */
    private int minNextFloorWithPassengers() {
        for (int i = 0; i < currentFloor; i++) {
            if (queues.get(i).size() != 0) {
                return i;
            }
        }
        return currentFloor;
    }

    /* Calculates maximum floor for passengers inside lift */
    private int maxNextFloorForPassengers() {
        return peopleInside.stream()
                .mapToInt(passenger -> passenger.desiredFloor)
                .max()
                .orElse(currentFloor);
    }

    /* Calculates minimum floor for passengers inside lift */
    private int minNextFloorForPassengers() {
        return peopleInside.stream()
                .mapToInt(passenger -> passenger.desiredFloor)
                .min()
                .orElse(currentFloor);
    }

    /* Adds passengers from current floor to lift */
    private void pickPassengers() {
        final List<Passenger> floor = queues.get(currentFloor);
        if (floor.size() != 0) {
            msg(String.format("Trying to add passengers at floor %d, current direction is %s", currentFloor, direction));
            for (int i = 0; i < floor.size(); i++) {
                if (countPassengersInside() == capacity) {
                    msg("Stopped adding new passengers");
                    break;
                }

                Passenger current = (Passenger) floor.get(i);

                if (isSameDirection(current)) {
                    peopleInside.add(current);
                    floor.set(i, null);
                    msg(String.format("Passenger entered lift: %s", current.toString()));
                    recalculateLimitFloor();
                } else {
                    msg(String.format("Skipped passenger: %s", current.toString()));
                }
            }
            floor.removeIf(Objects::isNull);
        } else {
            msg(String.format("Floor %d is empty, current direction is %s", currentFloor, direction));
        }
    }

    /* Removes passengers from lift if their desired floor matches current floor */
    private void dropPassengers() {
        if (countPassengersInside() != 0) {
            IntStream.range(0, countPassengersInside())
                    .filter(i -> peopleInside.get(i).desiredFloor == currentFloor)
                    .forEach(i -> {
                        final Passenger passenger = peopleInside.get(i);
                        msg(String.format("Passenger left the lift at floor %d: %s", currentFloor, passenger.toString()));
                        peopleInside.set(i, null);
                    });
            peopleInside.removeIf(Objects::isNull);
        } else {
            msg("No passengers inside - nobody to leave");
        }
    }

    /* Checks if everybody is on the desired floor */
    private boolean isNotFinished() {
        return countPassengersInside() == 0 && queues.entrySet().stream()
                .flatMap(floor -> floor.getValue().stream())
                .count() == 0;
    }

    /* Checks if passenger's direction matches current lift's direction */
    private boolean isSameDirection(Passenger passenger) {
        return passenger.direction == direction;
    }

    /* Counts passengers inside lift */
    private int countPassengersInside() {
        return (int) peopleInside.stream()
                .filter(p -> p != null)
                .count();
    }

    /* Converts visited floors to array */
    private int[] getVisitedFloors() {
        int[] result = new int[visitedFloors.size()];
        for (int i = 0; i < visitedFloors.size(); i++) {
            result[i] = visitedFloors.get(i);
            System.out.print(result[i] + " ");
        }
        return result;
    }

    /* Prints queue state */
    public void printQueue() {
        for (int i = this.queues.size() - 1; i >= 0; i--) {
            System.out.print(String.format("%d |", i));
            if (this.queues.get(i).size() != 0) {
                for (int j = this.queues.get(i).size() - 1; j >= 0; j--) {
                    Passenger passenger = (Passenger) this.queues.get(i).get(j);
                    System.out.println(String.format(" %s", passenger.toString()));
                }
            } else {
                System.out.println(" Nobody");
            }
        }
    }
}
