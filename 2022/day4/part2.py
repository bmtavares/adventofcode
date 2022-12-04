import os

puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').read().split()

total = 0

for line in puzzle_input:
    pair = line.split(',')

    elf_a = list(map(int,pair[0].split('-')))
    elf_b = list(map(int,pair[1].split('-')))

    if len(set(range(elf_a[0],elf_a[1]+1)).intersection(set(range(elf_b[0],elf_b[1]+1)))):
        total += 1

print(f'Total # of any overlapping tasks is {total}')