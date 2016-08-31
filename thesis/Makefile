screen: document.tex
	pdflatex document.tex
	biber document
	pdflatex document.tex
	pdflatex document.tex

print: document-print.tex
	pdflatex document-print.tex
	biber document-print
	pdflatex document-print.tex
	pdflatex document-print.tex
	bash -O extglob -c 'rm document-print.!(tex|pdf)'

both: screen print
