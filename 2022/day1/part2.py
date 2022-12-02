import os

puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').readlines()

current_elf_calories = 0

top_elf_calories = []

for line in puzzle_input:
    if line == '\n':
        if len(top_elf_calories) < 3:
            top_elf_calories.append(current_elf_calories)
            top_elf_calories.sort()
        elif current_elf_calories > top_elf_calories[0]:
            top_elf_calories.append(current_elf_calories)
            top_elf_calories.sort()
            top_elf_calories.pop(0)
        current_elf_calories = 0
        continue
    calories = int(line)
    current_elf_calories += calories

print(f'Top 3 elfs carry the following ammount of calories:')
sum_calories = 0
for elf in top_elf_calories:
    sum_calories += elf
    print(elf)

print(f'Total sum of top 3 carried calories: {sum_calories}')