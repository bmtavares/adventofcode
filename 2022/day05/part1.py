import os

def get_stacks(definition : str) -> list:
    rows = definition[-1].split()
    stacks = []
    for row in rows:
        i = len(definition) - 2
        j = definition[-1].index(row)
        stack = []
        while(i >= 0):
            if (definition[i][j] == ' '):
                break
            stack.append(definition[i][j])
            i-=1
        stacks.append(stack)
    return stacks

def move_crates(stack : list, ammount : int, start : int, finish : int, move_in_place : bool = False) -> None:
    if move_in_place:
        on_position = len(stack[start]) - ammount
    else:
        on_position = -1
    for _ in range(ammount):
        stack[finish].append(stack[start].pop(on_position))


if __name__ == '__main__':
    puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').read().split('\n')
    puzzle_input.pop()

    stacks = get_stacks(puzzle_input[:puzzle_input.index('')])

    for movement in puzzle_input[puzzle_input.index('')+1:]:
        movement = movement.split()
        move_crates(stacks, int(movement[1]), int(movement[3])-1, int(movement[5])-1)

    top_most = ''

    for stack in stacks:
        top_most += stack[-1]

    print(f"The top crates spell the word: {top_most}")
