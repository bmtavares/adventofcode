import os

rules: dict = {}
sum = 0

def validate_manual(pages: str) -> bool:
    for i in range(0, len(pages)):
        if rules.setdefault(pages[i]):
            for p in pages[0:i]:
                if p in rules[pages[i]]:
                    return False
    return True

with open(os.path.dirname(__file__) + '/puzzleinput.txt','r') as puzzle_input:
    while (rule := puzzle_input.readline().rstrip().split("|")) != ['']:
        rules[rule[0]]: rules.setdefault(rule[0], set()).add(rule[1])
        
    while (pages := puzzle_input.readline().rstrip().split(",")) != ['']:
        if validate_manual(pages):
            sum += int(pages[len(pages)//2])

    print(sum)
