
import qualified Data.Map as M

update c xs@(y:_) = let p = c : y in p : rec c p xs
    where rec c p (x:[])       = [minimum [x, 'a' : x, 'a' : p]]
          rec c p (x:xs@(y:_)) = let p' = minimum [c : y, x, 'a' : x, 'a' : p]
                                 in p' : rec c p' xs

main = do s <- getLine
          k <- readLn
          putStrLn (foldr update [""] s !! k)