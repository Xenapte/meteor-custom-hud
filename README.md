# Meteor Custom HUD

Some HUD customizations that I find to be useful personally.

## Custom HUD List

- Custom Coords Formatter
- Custom Time Formatter
- Weather Display
- Last Death Location

## Custom Starscipt Functions

- `format(fmt, ...args)`: format arbitrary values (see `java.util.Formatter`).
- `formatCoords(fmt)`: format coords (as a shorthand to `format(fmt, player.x, player.y, player.z)`).
- `formatOppositeCoords(fmt)`
- `formatTime(fmt)`: format the current world time (see `java.text.SimpleDateFormat`).
- `weather`
- `player.last_death_pos`
  - `player.last_death_pos.dimension`
  - `player.last_death_pos.x` / `y` / `z`
  - `player.last_death_pos.format(fmt)`

## Building  

- Clone this project
- `./gradlew build`
- Run the compiled mod with Meteor
