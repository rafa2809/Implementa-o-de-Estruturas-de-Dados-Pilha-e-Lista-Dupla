package lista9;

public class lista9 {
    class Celula<E> {
    private Celula<E> prox;
    private E elemento;

    public Celula(E elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula<E> getProx() {
        return prox;
    }

    public void setProx(Celula<E> prox) {
        this.prox = prox;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }
}

public class Pilha<T> {
    private Celula<T> topo;
    private int tamanho;

    public Pilha() {
        topo = null;
        tamanho = 0;
    }

    public void inserir(T elemento) {
        Celula<T> novaCelula = new Celula<>(elemento);
        novaCelula.setProx(topo);
        topo = novaCelula;
        tamanho++;
    }

    public T remover() {
        if (topo == null)
            throw new RuntimeException("A pilha está vazia");

        T elementoRemovido = topo.getElemento();
        topo = topo.getProx();
        tamanho--;
        return elementoRemovido;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void mostrarElementos() {
        Celula<T> atual = topo;
        while (atual != null) {
            System.out.print(atual.getElemento() + " ");
            atual = atual.getProx();
        }
        System.out.println();
    }
}

class CelulaDupla<T> {
    private T elemento;
    private CelulaDupla<T> ant;
    private CelulaDupla<T> prox;

    public CelulaDupla(T elemento) {
        this.elemento = elemento;
        this.ant = null;
        this.prox = null;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public CelulaDupla<T> getAnt() {
        return ant;
    }

    public void setAnt(CelulaDupla<T> ant) {
        this.ant = ant;
    }

    public CelulaDupla<T> getProx() {
        return prox;
    }

    public void setProx(CelulaDupla<T> prox) {
        this.prox = prox;
    }
}

class ListaDupla<T> {
    private CelulaDupla<T> primeiro;
    private CelulaDupla<T> ultimo;

    public ListaDupla() {
        primeiro = null;
        ultimo = null;
    }

    public void inserirInicio(T elemento) {
        CelulaDupla<T> novaCelula = new CelulaDupla<>(elemento);
        if (primeiro == null) {
            primeiro = novaCelula;
            ultimo = novaCelula;
        } else {
            novaCelula.setProx(primeiro);
            primeiro.setAnt(novaCelula);
            primeiro = novaCelula;
        }
    }

    public void removerInicio() {
        if (primeiro == null)
            throw new RuntimeException("A lista está vazia");

        if (primeiro == ultimo) {
            primeiro = null;
            ultimo = null;
        } else {
            primeiro = primeiro.getProx();
            primeiro.setAnt(null);
        }
    }

    public void inserirFim(T elemento) {
        CelulaDupla<T> novaCelula = new CelulaDupla<>(elemento);
        if (ultimo == null) {
            primeiro = novaCelula;
            ultimo = novaCelula;
        } else {
            ultimo.setProx(novaCelula);
            novaCelula.setAnt(ultimo);
            ultimo = novaCelula;
        }
    }

    public void removerFim() {
        if (ultimo == null)
            throw new RuntimeException("A lista está vazia");

        if (primeiro == ultimo) {
            primeiro = null;
            ultimo = null;
        } else {
            ultimo = ultimo.getAnt();
            ultimo.setProx(null);
        }
    }

    public void inserirPosicao(int posicao, T elemento) {
        if (posicao < 0 || posicao > tamanho())
            throw new IllegalArgumentException("Posição inválida");

        if (posicao == 0) {
            inserirInicio(elemento);
        } else if (posicao == tamanho()) {
            inserirFim(elemento);
        } else {
            CelulaDupla<T> atual = primeiro;
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.getProx();
            }
            CelulaDupla<T> novaCelula = new CelulaDupla<>(elemento);
            novaCelula.setAnt(atual);
            novaCelula.setProx(atual.getProx());
            atual.getProx().setAnt(novaCelula);
            atual.setProx(novaCelula);
        }
    }

    public void removerPosicao(int posicao) {
        if (posicao < 0 || posicao >= tamanho())
            throw new IllegalArgumentException("Posição inválida");

        if (posicao == 0) {
            removerInicio();
        } else if (posicao == tamanho() - 1) {
            removerFim();
        } else {
            CelulaDupla<T> atual = primeiro;
            for (int i = 0; i < posicao; i++) {
                atual = atual.getProx();
            }
            atual.getAnt().setProx(atual.getProx());
            atual.getProx().setAnt(atual.getAnt());
        }
    }

    public int tamanho() {
        int tamanho = 0;
        CelulaDupla<T> atual = primeiro;
        while (atual != null) {
            tamanho++;
            atual = atual.getProx();
        }
        return tamanho;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public void mostrarElementos() {
        CelulaDupla<T> atual = primeiro;
        while (atual != null) {
            System.out.print(atual.getElemento() + " ");
            atual = atual.getProx();
        }
        System.out.println();
    }
}

public class Teste {
    public void main(String[] args) {
        // Teste da Pilha
        System.out.println("Teste da Pilha:");
        Pilha<String> pilhaString = new Pilha<>();
        pilhaString.inserir("A");
        pilhaString.inserir("B");
        pilhaString.inserir("C");
        pilhaString.mostrarElementos();
        System.out.println("Removido: " + pilhaString.remover());
        System.out.println("Tamanho: " + pilhaString.tamanho());
        System.out.println("Vazia " + pilhaString.estaVazia());
        pilhaString.mostrarElementos();

}
}
}