% Program 1 Part 2 done using Octave
% Plot the x and y points onto a graph
x=-10:1:10;
y=(x*2)+5;
plot(x,y);
xlabel("x");
ylabel("(x*2)+5");
title("Plotter");

% Combine x and y then output to CSV
union = [x;y];
csvwrite('output.csv', union);

% CSV Input read
z = csvread('output.csv');

% Salt
salt = [];
for i = 2:2:length(z)*2
  salt(end+1) = z(i) + randi(10)
endfor

% Smooth
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
