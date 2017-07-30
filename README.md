# SuffixTrie

Suffix Trie definition can be found at https://en.wikipedia.org/wiki/Suffix_tree. 
The Suffix Trie was implemented in two versions, optimized and unoptimized Suffix Trie. 
Unoptimized Suffix Trie has only 1 character per edge while optimized suffix trie can 
have multiple characters per edge. Maven was used as a dependency manager. 
The JUnit framework was added and used for testing. Javadocs is also available 
and can be found at https://ferendo.github.io/SuffixTrie/.

## Design and Technical Approach

A Suffix Trie is made up of several nodes and each node has several outgoing edges (unique
keys) to other nodes. Each key maps to a value and every key is unique thus the map data
structure is good to hold the Suffix Trie data structure. To leave the Node abstract class
generalized, generics were used. The type parameters are K and V which represents Key and
Value. The terminal character is set to ~. No ways connecting the terminal character will be
accepted. Suffix Trie have common functionality between different versions of it, therefore an
interface containing these functions was made.

Starting off with the unoptimized Suffix Trie refer to the figure below. Each outgoing edge from the node
contains only one character. The class SuffixNode extends Node with type parameters
Character as K and SuffixNode as V. SuffixTrieImpl was also created. It holds only the
root/head SuffixNode needed to be able to use the Trie. The head is a single SuffixNode that
contains other SuffixNodes.

To create the optimized Suffix Trie 2 new Nodes are needed. The first optimization consists of
putting non-branching paths into a single edge. Therefore a key can hold multiple characters.
To create this node, OptimizedNode extends Node with type parameters String as K and
OptimizedNode as V. This is very similar to SuffixNode. The second optimization continued
on the previous optimization where the key is set to (offset, length). This key is not a primitive
data structure thus it needed to be created. The OffsetLengthKey class consists of two Integers
which represents offset and length. As can be seen from the UML, this key implements
Comparable with the type parameter T being the key itself. This was done so that two keys can
be compared and sorted numerically. The sorting will be done automatically by a TreeMap.
Using this key, OffsetLengthNode can extends Node where K is OffsetLengthKey and V is
OffsetLengthNode. With the Node completed, the OptimizedSuffixTrieImpl implements
SuffixTrie. Once again OptimizedSuffixTrieImpl holds only the root/head OffsetLengthNode.
The given word used to build the Suffix Trie is also kept since this is needed to transfer (offset,
length) to String.

![Image](docs/UML_Part1.jpg?raw=true "UML diagram for Suffix part 1")

![Image](docs/UML_Part2.jpg?raw=true "UML diagram for Suffix part 2")