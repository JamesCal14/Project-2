x=-10:1:10;
y=(x*2)+5;
plot(x,y);
xlabel("x");
ylabel("(x*2)+5");
title("Plotter");
union = [x;y];

csvwrite('output.csv', union);

z = csvread('output.csv');

salt = [];
for i = 2:2:length(z)*2
  salt(end+1) = z(i) + randi(10)
endfor

smooth = [];
windowValue = 3;
for i = 1:length(salt)
  result = 0;
  count = 0;
  if (i - windowValue <= 1)
    for j = 1:min(i + windowValue, length(salt))
      result += salt(j);
      count++;
    endfor
  else
    for j = max(i - windowValue, 1):min(i + windowValue, length(salt))
      result += salt(j);
      count++;
    endfor
  endif
  result /= count;
  smooth(end + 1) = result;
endfor
