#!/bin/bash
# run_bai_ci.sh
echo "--- Đang đẩy code lên GitHub để chạy CI ---"
git add .
git commit -m "Thực hiện bài tập CI/CD"
git push origin master
echo "--- Hãy kiểm tra tab Actions trên GitHub của bạn! ---"