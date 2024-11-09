import json
import os

# Đường dẫn đến file JSONL của bạn
input_file = 'C:/Users/ADMIN/DictionaryApp/src/resources/com/dictionary/resources/data/game_data/Cambridge_Multiple_Choice_Questions_Reading_Dataset.jsonl'

# Tạo các dictionary để lưu các đối tượng theo từng level
levels = {'B1': [], 'B2': [], 'C1': [], 'C2': []}

# Đọc từng dòng trong file JSONL
with open(input_file, 'r', encoding='utf-8') as file:
    for line in file:
        data = json.loads(line)
        level = data.get('level')
        if level in levels:
            levels[level].append(data)

# Ghi các đối tượng vào các file JSONL khác
for level, items in levels.items():
    output_file = f'C:/Users/ADMIN/DictionaryApp/src/resources/com/dictionary/resources/data/game_data/{level}.jsonl'
    with open(output_file, 'w', encoding='utf-8') as file:
        for item in items:
            json.dump(item, file)
            file.write('\n')
    print(f'Đã ghi {len(items)} đối tượng vào file {output_file}')

print('Quá trình phân tách hoàn tất.')
