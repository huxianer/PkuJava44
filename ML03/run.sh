#!/bin/bash
python char.py
python data_div2.py
python char_2_word.py
python replace.py
python replace2.py

python fmm.py train_seg.data test_pre.data test_result.data
perl score train_seg.data test_gold.data test_result.data > score1.utf8
tail -30 score1.utf8
python fmm.py train_utf16.seg test_utf16.seg fmm_result.seg

#也可以是其他的语言