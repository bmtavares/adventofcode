import os

puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').readlines()

max_calories = 0

current_elf_calories = 0

for line in puzzle_input:
    if line == '\n':
        if current_elf_calories > max_calories:
            max_calories = current_elf_calories
        current_elf_calories = 0
        continue
    calories = int(line)
    current_elf_calories += calories

print(f'Max calories carried is: {max_calories}')