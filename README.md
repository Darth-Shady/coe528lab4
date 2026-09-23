# COE528 — Lab 4: Composite & Singleton Patterns

Two design-pattern exercises in one package: a **Composite** pattern for grouping food items into nested categories with recursive pricing/printing, and a **Singleton** pattern for a single shared file-backed record.

## Package

```
lab4
```

## Files

| File | Role |
|---|---|
| `FoodComponent.java` | Abstract base of the Composite pattern — declares `getPrice()` and `print(int level)` |
| `FoodItem.java` | Leaf of the composite — a single priced food item |
| `FoodCategory.java` | Composite node — holds a list of `FoodComponent`s (items or nested categories), sums their prices, and prints recursively with indentation |
| `Driver.java` | Builds a sample food hierarchy and prints it |
| `Record.java` | Singleton pattern — a single, lazily-created instance wrapping append/read access to `record.txt` |

## Part 1: Composite Pattern

**`FoodComponent`** (abstract) — the common type for both leaves and composites:
- `getPrice(): double`
- `print(int level): void`

**`FoodItem`** (leaf) — wraps a `name` and fixed `price`. `getPrice()` returns that price directly; `print(level)` prints the item's name and price, indented by `level` tabs.

**`FoodCategory`** (composite) — wraps a `name` and an `ArrayList<FoodComponent>`:
- `add(FoodComponent fc)` / `remove(FoodComponent fc)` — manage children (which may themselves be `FoodItem`s or nested `FoodCategory`s).
- `getPrice()` — recursively sums the price of every child.
- `print(level)` — prints its own name and total price, indented by `level` tabs, then calls `print(level + 1)` on each child.

**`Driver`** builds this hierarchy and prints it from the root:

```
frozen
├── meat
│   ├── egg (4.5)
│   └── chicken (5.0)
├── vegetables
│   ├── blueberries (2.5)
│   ├── strawberries (3.5)
│   └── peas (6.0)
└── icecream (7.0)
```

Calling `fc1.print(0)` triggers the recursive traversal, printing each category's aggregated price alongside every leaf item, indented by nesting depth.

**Run with:**
```
javac lab4/Driver.java lab4/FoodComponent.java lab4/FoodCategory.java lab4/FoodItem.java
java lab4.Driver
```

## Part 2: Singleton Pattern

**`Record`** ensures only one instance ever manages access to `record.txt`:
- The constructor is `private`, so it can't be called from outside the class.
- `getInstance()` lazily creates the sole instance on first call (`filename = "record.txt"`) and returns the same instance on every subsequent call.
- `write(String msg)` appends `msg` to the file (opens `FileWriter` in append mode).
- `read()` prints every line of the file to standard output.
- `main()` demonstrates the pattern: it fetches the singleton via `getInstance()` (never `new Record(...)`), appends two lines, then reads back and prints the whole file.

**Run with:**
```
javac lab4/Record.java
java lab4.Record
```

**Expected output** (assuming `record.txt` starts empty or doesn't exist):
```
currently the file record.txt contains the following lines:
Hello-1
Hello-2
```
Re-running it will append two more lines each time, since `write` always opens in append mode — `record.txt` accumulates across runs rather than resetting.

## Known Quirks

- `Driver` casts `fc1`, `fc2`, and `fc3` from `FoodComponent` back to `FoodCategory` to call `add()`, since `add`/`remove` aren't part of the `FoodComponent` interface — a common, expected trade-off in this flavor of the Composite pattern (leaves and composites share a type, but only composites expose child-management methods).
- `Record`'s singleton isn't thread-safe (no synchronization around the lazy-init check in `getInstance()`), which is fine for this single-threaded demo but worth flagging if reused elsewhere.
- `record.txt` is not cleared between runs, so repeated executions of `Record.main()` will keep appending rather than starting fresh.
