# Recyclerview with expandable items
Example implementation on how to create expandable items in a Recyclerview.

I'll still write more in details about this implementation, but for now the main points covered in this repo are:

- expanding/collapsing items in a Recyclerview by changing the height of a view using the Animation class
- use o ViewModel and LiveData to send the toggle action from the ViewModel to the View
- use of a custom View (compound view) to isolate the expandable view item
- use of a custom class to manage the animation of expanding/collapsing in order
- the Recyclerview is scrolled according to the position of the expanded item so it becomes visible when its changes its height

