import os

puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').readlines()

total_score = 0

at_value = ord('@')

for line in puzzle_input:
    elf_play = ord(line[0]) - at_value

    if line[2] == 'Y':
        total_score += elf_play + 3
    elif line[2] == 'Z':
        total_score += (elf_play % 3) + 7 # (+ 1 + 6)
    else:
        if elf_play == 1:
            total_score += 3
        else:
            total_score += elf_play - 1

print(f'Total score is {total_score}')