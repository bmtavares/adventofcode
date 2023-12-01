import os

puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').read().split()

priority_sum = 0

for rucksack in puzzle_input:
    compartment_size = len(rucksack)//2
    first_compartment = rucksack[compartment_size:]
    second_compartment = rucksack[:compartment_size]
    for item in second_compartment:
        if item in first_compartment:
            if item < 'a':
                priority_sum += ord(item) - 38
            else:
                priority_sum += ord(item) - 96
            break

print(f'Priority sum of mishandled items is: {priority_sum}')