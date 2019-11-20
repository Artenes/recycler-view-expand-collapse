# Recyclerview with expandable items
Example implementation on how to create expandable items in a RecyclerView.

## Features

### #1 Expand/collapse items with a slide animation
![Expand/collapse items with a slide animation](images/expandcollapse.gif)

### #2 Only one item can be expanded at a time
![Only one item can be expanded at a time](images/oneatime.gif)

### #3 Item moves as it is expanded
![Item moves as it is expanded](images/scrollsto.gif)

### #4 Day/Night support
![Day/Night support](images/nightmode.gif)

## Implementation details

### #1 Architecture

``MVVM`` was used. The ``ViewModel`` fetches data from a ``Repository``, transforms it and then exposes to the ``View`` for it to observe through a ``LiveData``.

### #2 View

A ``compound view`` was made to contain the layout used in each item in the ``RecyclerView``. This makes easier to get the necessary parts of the view to animate.

### #3 Animation

Was used ``ValueAnimator`` to in each frame change the height of the item to give the impression of expanding/collapsing.

In the expand animation the ``RecyclerView`` is moved along the animated item so if gets off screen, it gets back to it again.

Delay was used in the expand animation so it would start only after the animation of any other item closing.

### #4 Adapter

``DiffUtil`` and ``Payloads`` were used to indicate to a ``ViewHolder`` than an item must be animated.