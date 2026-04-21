import javax.swing.*
import java.awt.*

class Atividade(val nome: String) {
    var concluida: Boolean = false

    override fun toString(): String {
        if (concluida) {
            return "[OK] $nome"
        } else {
            return "[  ] $nome"
        }
    }
}

fun main() {

    val listaDeTarefas = mutableListOf<Atividade>()
    val modeloParaATela = DefaultListModel<String>()

    val janela = JFrame("Meu Gerenciador")
    janela.setSize(500, 400)
    janela.layout = FlowLayout()
    janela.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    val titulo = JLabel("Lista de Atividades")
    val caixaLista = JList(modeloParaATela)
    val scroll = JScrollPane(caixaLista)
    scroll.preferredSize = Dimension(400, 200)

    val botaoAdicionar = JButton("Nova Tarefa")
    val botaoConcluir = JButton("Concluir")
    val botaoRemover = JButton("Apagar")

    fun atualizarTela() {
        modeloParaATela.clear()
        for (item in listaDeTarefas) {
            modeloParaATela.addElement(item.toString())
        }
    }

    botaoAdicionar.addActionListener {
        val texto = JOptionPane.showInputDialog("O que você precisa fazer?")
        if (texto != null && texto != "") {
            val novaAtividade = Atividade(texto)
            listaDeTarefas.add(novaAtividade)
            atualizarTela()
        }
    }

    botaoConcluir.addActionListener {
        val posicao = caixaLista.selectedIndex
        if (posicao != -1) {
            listaDeTarefas[posicao].concluida = true
            atualizarTela()
        }
    }

    botaoRemover.addActionListener {
        val posicao = caixaLista.selectedIndex
        if (posicao != -1) {
            listaDeTarefas.removeAt(posicao)
            atualizarTela()
        }
    }

    janela.add(titulo)
    janela.add(scroll)
    janela.add(botaoAdicionar)
    janela.add(botaoConcluir)
    janela.add(botaoRemover)

    janela.isVisible = true
}