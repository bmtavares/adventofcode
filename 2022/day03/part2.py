import os

puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').read().split()

priority_sum = 0
window_size = 3

for i in range(0, len(puzzle_input) - window_size + 1, window_size):
    group_rucksacks = puzzle_input[i: i + window_size]

    smallest_rucksack = min(group_rucksacks)

    group_rucksacks.remove(smallest_rucksack)

    for item in smallest_rucksack:
        if item in group_rucksacks[0] and item in group_rucksacks[1]:
            if item < 'a':
                priority_sum += ord(item) - 38
            else:
                priority_sum += ord(item) - 96
            break

print(f'Priority sum of groups is: {priority_sum}')