import os

puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').readlines()

total_score = 0

at_value = ord('@')
w_value = ord('W')

for line in puzzle_input:
    player_value = ord(line[2]) - w_value
    play_value = (ord(line[0]) - at_value) - player_value
    if play_value == 0:
        total_score += 3
    elif play_value in [-1,2]:
        total_score += 6
    total_score += player_value

print(f'Total score is {total_score}')