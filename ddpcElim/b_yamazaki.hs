
import Control.Applicative
import qualified Data.Foldable as F
import Control.Monad.State

import Data.Char
import qualified Data.Map as M
import Data.List
import qualified Data.Traversable as T

splitBy _ [] = []
splitBy f xs = let (y, ys) = break f (dropWhile f xs) in
               y : splitBy f ys

ifoldl f init xs = rec 0 init xs
    where rec _ init []     = init
          rec i init (x:xs) = rec (i+1) (f i init x) xs

valuesToRooms :: [Int] -> [(Int, [Int])]
valuesToRooms values = M.toAscList (ifoldl helper M.empty values)
    where helper i m v = M.insertWith (++) v [i] m

toRoomss :: [Int] -> [[Int]]
toRoomss values = map (sort . snd) (valuesToRooms values)

f :: [Int] -> Int -> (Int, Int)
f xs x = if head xs >= x then (0, last xs)
         else case snd <$> find ((>= x) . fst) (zip (tail xs) xs) of
                  Nothing -> (1, last xs)
                  Just y  -> (1, y)

main = do getLine
          as <- map read . splitBy isSpace <$> getLine :: IO [Int]
          let (l, p) = runState (sum <$> T.traverse (state . f) (toRoomss as)) 0
          print $ (if p == 0 then 0 else 1) + l