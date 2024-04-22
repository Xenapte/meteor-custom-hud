# Meteor Custom HUD

Some HUD customizations that I find to be useful personally.

## Custom HUD List

- Custom Coords
- Custom Time

## Custom Starscipt Functions

- `format(fmt, ...args)`: format arbitrary values (see `java.util.Formatter`).
- `formatCoords(fmt)`: format coords (as a shorthand to `format(fmt, player.x, player.y, player.z)`).
- `formatOppositeCoords(fmt)`
- `formatTime(fmt)`: format the current world time (see `java.text.SimpleDateFormat`).

## Building  

- Clone this project
- `./gradlew build`
- Run the compiled mod with Meteor
