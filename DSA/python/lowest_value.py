"""Module providing a function to print the lowest value in a list of integers."""

my_array = [7, 12, 9, 4, 11]
# `min_val` is being used to keep track of the lowest value found in the `my_array` list. It is
# initialized with the first element of the list and then updated in the loop to store the lowest
# value encountered so far. Finally, the lowest value is printed out after the loop completes.
MIN_VAL = my_array[0]

for i in my_array:
    if i < MIN_VAL:
        MIN_VAL = i
print("Lowest value:", MIN_VAL)
