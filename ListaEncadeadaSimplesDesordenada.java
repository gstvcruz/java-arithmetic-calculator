public class ListaEncadeadaSimplesDesordenada <X> implements Cloneable
{
	private class No implements Cloneable
	{
	    private X  info;
	    private No prox;
	    
	    public No (X i, No p)
	    {
	        this.info=i;
	        this.prox=p;
	    }
	    
	    @SuppressWarnings("unused")
		public No (X i)
	    {
	        this.info=i;
	        this.prox=null;
	    }
	    
	    public X getInfo ()
	    {
	        return this.info;
	    }
	    
	    public No getProx ()
	    {
	        return this.prox;
	    }
	    
	    @SuppressWarnings("unused")
		public void setInfo (X i)
	    {
	        this.info=i;
	    }
	    
	    public void setProx (No p)
	    {
	        this.prox=p;
	    }
	}
	
	private No primeiro;
	
	private No ultimo;

	public ListaEncadeadaSimplesDesordenada ()
	{
		this.primeiro = null;
		this.ultimo = null;
	}
	
	public void guardeNoInicio (X i) throws Exception
	{
		if (i==null) throw new Exception ("Informação ausente");
		
		X copia;
		if (i instanceof Cloneable)
		    copia = new Clonador<X>().clone(i);
		else
		    copia = i;

		this.primeiro = new No (copia,this.primeiro);

		if (this.ultimo == null)
			this.ultimo = this.primeiro;
	}
	
	public void guardeNoFinal (X i) throws Exception
	{
		if (i==null) throw new Exception ("Empty information");

		X copia;
		if (i instanceof Cloneable)
			copia = new Clonador<X>().clone(i);
		else
			copia = i;

		if (this.primeiro == null) {
			this.primeiro = new No (copia, this.primeiro);
			this.ultimo = this.primeiro;
			return;
		}

		No ultimo = new No (copia, null);
		this.ultimo.setProx(ultimo);
		this.ultimo = ultimo;
	}

	public X getPrimeiro () throws Exception
	{
		if (this.primeiro==null) throw new Exception ("Lista vazia");
		
		X copia;
		if (this.primeiro.getInfo() instanceof Cloneable)
		    copia = new Clonador<X>().clone(this.primeiro.getInfo());
		else
		    copia = this.primeiro.getInfo();
		    
		return copia;
	}

	public X getUltimo () throws Exception
	{
		if (this.ultimo==null) throw new Exception("");

		X copia;
		if (this.ultimo.getInfo() instanceof Cloneable)
			copia = new Clonador<X>().clone(this.ultimo.getInfo());
		else
			copia = this.ultimo.getInfo();

		return copia;
	}
	
	public int getTamanho()
	{
		if(this.primeiro == null)
		{
			return 0;
		}
		
		No atual = this.primeiro;
		int elementos = 0;
		
		while(atual != null)
		{
				atual = atual.getProx();
				elementos++;
		}
		
		return elementos;
	}
	
	// posições serão numeradas 0, 1, 2, etc
	public void remova (int posicao) throws Exception
	{
		if(posicao < 0 || posicao >= this.getTamanho())
		{
			throw new IndexOutOfBoundsException("Posição inválida!");
		}
			
			
		if(posicao == 0)
		{
			this.primeiro = this.primeiro.getProx();
		} else{
			No atual = this.primeiro;
			for(int i = 0; i < posicao - 1; i++){
			    atual = atual.getProx();
			}
		
			atual.setProx(atual.getProx().getProx()); 
		}
	}
	
	public boolean tem (X i)
	{
		No atual=this.primeiro;
		
		while (atual!=null)
		{
			if (atual.getInfo().equals(i)) return true;
			
			atual=atual.getProx();
		}
		
		return false;
	}
	
	public void rotacao(int v) {
		No atual = primeiro;
		No ultimo = null;
		for (; v != 0; v--) {
			while (atual.getProx() != null) {
				if (atual.getProx().getProx() == null) {
					ultimo = atual;
				}
				atual = atual.getProx();
			}
			atual.setProx(primeiro);
			ultimo.setProx(null);
			primeiro = atual;
		}
	}

    @Override
    public String toString ()
    {
		if (this.primeiro==null) return "[]";
		
		String ret="[";
		
		ret+=this.primeiro.getInfo();
		
		No atual = this.primeiro.getProx();
		
		while (atual!=null)
		{
			ret+=", "+atual.getInfo();
			atual=atual.getProx();
		}
		
		return ret+"]";
	}
}
